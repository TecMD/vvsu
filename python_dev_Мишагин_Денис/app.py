# app.py
from flask import Flask, request, jsonify
import sqlite3
import os

app = Flask(__name__)

DB1_PATH = 'db1.sqlite'
DB2_PATH = 'db2.sqlite'


def get_db1_connection():
    conn = sqlite3.connect(DB1_PATH)
    conn.row_factory = sqlite3.Row
    return conn


def get_db2_connection():
    conn = sqlite3.connect(DB2_PATH)
    conn.row_factory = sqlite3.Row
    return conn


def get_user_id_by_login(login):
    conn = get_db1_connection()
    cursor = conn.cursor()
    cursor.execute("SELECT id FROM users WHERE login = ?", (login,))
    result = cursor.fetchone()
    conn.close()
    return result['id'] if result else None


@app.route('/api/comments', methods=['GET'])
def get_comments_dataset():
    login = request.args.get('login')

    if not login:
        return jsonify({'error': 'Необходимо указать параметр login'}), 400

    user_id = get_user_id_by_login(login)
    if not user_id:
        return jsonify({'error': f'Пользователь с логином {login} не найден'}), 404

    conn2 = get_db2_connection()
    cursor2 = conn2.cursor()

    cursor2.execute("SELECT id FROM event_type WHERE name = 'comment'")
    comment_type_id = cursor2.fetchone()['id']

    cursor2.execute("SELECT id FROM space_type WHERE name = 'post'")
    post_space_id = cursor2.fetchone()['id']

    query = '''
    SELECT 
        u.login as user_login,
        p.header as post_header,
        u_author.login as author_login,
        COUNT(l.id) as comments_count
    FROM logs l
    JOIN space_type st ON l.space_type_id = st.id
    JOIN event_type et ON l.event_type_id = et.id
    JOIN users u ON l.user_id = u.id
    LEFT JOIN post p ON 1=1  -- В реальной системе здесь была бы связь
    LEFT JOIN users u_author ON p.author_id = u_author.id
    WHERE l.user_id = ? 
        AND et.name = 'comment'
        AND st.name = 'post'
    GROUP BY u.id, p.id
    '''

    try:
        cursor2.execute(query, (user_id,))
        results = cursor2.fetchall()

        comments_data = []
        for row in results:
            comments_data.append({
                'логин_пользователя': row['user_login'],
                'заголовок_поста': row['post_header'] if row['post_header'] else 'Неизвестный пост',
                'логин_автора_поста': row['author_login'] if row['author_login'] else 'Неизвестный автор',
                'количество_комментариев': row['comments_count']
            })

        return jsonify({
            'login': login,
            'comments_dataset': comments_data
        })

    except Exception as e:
        return jsonify({'error': str(e)}), 500
    finally:
        conn2.close()


@app.route('/api/general', methods=['GET'])
def get_general_dataset():
    login = request.args.get('login')

    if not login:
        return jsonify({'error': 'Необходимо указать параметр login'}), 400

    user_id = get_user_id_by_login(login)
    if not user_id:
        return jsonify({'error': f'Пользователь с логином {login} не найден'}), 404

    conn2 = get_db2_connection()
    cursor2 = conn2.cursor()

    cursor2.execute("SELECT id FROM event_type WHERE name = 'login'")
    login_id = cursor2.fetchone()
    login_id = login_id['id'] if login_id else None

    cursor2.execute("SELECT id FROM event_type WHERE name = 'logout'")
    logout_id = cursor2.fetchone()
    logout_id = logout_id['id'] if logout_id else None

    cursor2.execute("SELECT id FROM space_type WHERE name = 'blog'")
    blog_space_id = cursor2.fetchone()
    blog_space_id = blog_space_id['id'] if blog_space_id else None

    query = '''
    SELECT 
        DATE(l.datetime) as date,
        SUM(CASE WHEN l.event_type_id = ? THEN 1 ELSE 0 END) as login_count,
        SUM(CASE WHEN l.event_type_id = ? THEN 1 ELSE 0 END) as logout_count,
        SUM(CASE WHEN l.space_type_id = ? THEN 1 ELSE 0 END) as blog_actions_count
    FROM logs l
    WHERE l.user_id = ?
    GROUP BY DATE(l.datetime)
    ORDER BY date
    '''

    try:
        cursor2.execute(query, (login_id, logout_id, blog_space_id, user_id))
        results = cursor2.fetchall()

        general_data = []
        for row in results:
            general_data.append({
                'дата': row['date'],
                'количество_входов': row['login_count'],
                'количество_выходов': row['logout_count'],
                'количество_действий_внутри_блога': row['blog_actions_count']
            })

        return jsonify({
            'login': login,
            'general_dataset': general_data
        })

    except Exception as e:
        return jsonify({'error': str(e)}), 500
    finally:
        conn2.close()


@app.route('/api/health', methods=['GET'])
def health_check():
    try:
        conn1 = get_db1_connection()
        conn1.close()
        conn2 = get_db2_connection()
        conn2.close()
        return jsonify({'status': 'healthy', 'databases': 'connected'})
    except Exception as e:
        return jsonify({'status': 'unhealthy', 'error': str(e)}), 500


if __name__ == '__main__':
    if not os.path.exists(DB1_PATH) or not os.path.exists(DB2_PATH):
        print("Базы данных не найдены. Запустите setup_databases.py сначала.")
        exit(1)

    app.run(debug=True, host='0.0.0.0', port=5000)