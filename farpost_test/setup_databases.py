import sqlite3
import os


def setup_db1():
    conn = sqlite3.connect('db1.sqlite')
    cursor = conn.cursor()

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS users (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        login VARCHAR(255) NOT NULL UNIQUE,
        email VARCHAR(255) NOT NULL
    )
    ''')

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS blog (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        owner_id INTEGER NOT NULL,
        name VARCHAR(255) NOT NULL,
        description TEXT,
        FOREIGN KEY (owner_id) REFERENCES users(id)
    )
    ''')

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS post (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        header VARCHAR(255) NOT NULL,
        text TEXT,
        author_id INTEGER NOT NULL,
        blog_id INTEGER NOT NULL,
        FOREIGN KEY (author_id) REFERENCES users(id),
        FOREIGN KEY (blog_id) REFERENCES blog(id)
    )
    ''')

    conn.commit()
    conn.close()

def setup_db2():
    conn = sqlite3.connect('db2.sqlite')
    cursor = conn.cursor()

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS space_type (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name VARCHAR(50) NOT NULL UNIQUE
    )
    ''')

    space_types = ['global', 'blog', 'post']
    for st in space_types:
        cursor.execute('INSERT OR IGNORE INTO space_type (name) VALUES (?)', (st,))

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS event_type (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name VARCHAR(50) NOT NULL UNIQUE
    )
    ''')

    event_types = ['login', 'comment', 'create_post', 'delete_post', 'logout']
    for et in event_types:
        cursor.execute('INSERT OR IGNORE INTO event_type (name) VALUES (?)', (et,))

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS logs (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        datetime DATETIME NOT NULL,
        user_id INTEGER NOT NULL,
        space_type_id INTEGER NOT NULL,
        event_type_id INTEGER NOT NULL,
        FOREIGN KEY (space_type_id) REFERENCES space_type(id),
        FOREIGN KEY (event_type_id) REFERENCES event_type(id)
    )
    ''')

    conn.commit()
    conn.close()
    print("База данных db2.sqlite успешно создана")


def insert_test_data():

    conn1 = sqlite3.connect('db1.sqlite')
    cursor1 = conn1.cursor()

    users = [
        ('denis', 'denis@yandex.ru'),
        ('sofia', 'sofia@gmail.com'),
        ('anna', 'anna@mail.ru'),
    ]
    cursor1.executemany('INSERT OR IGNORE INTO users (login, email) VALUES (?, ?)', users)

    cursor1.execute(
        "INSERT OR IGNORE INTO blog (owner_id, name, description) VALUES (1, 'Блог Софии', 'Кулинарный блог Софии')")
    cursor1.execute(
        "INSERT OR IGNORE INTO blog (owner_id, name, description) VALUES (2, 'Блог Боба', 'Ремонтный блог Дениса')")

    # Вставка постов
    posts = [
        ('Первый пост Софии', 'Текст первого поста', 1, 1),
        ('Второй пост Софии', 'Текст второго поста', 1, 1),
        ('Пост Дениса', 'Текст поста Дениса', 2, 2),
    ]
    cursor1.executemany('INSERT OR IGNORE INTO post (header, text, author_id, blog_id) VALUES (?, ?, ?, ?)', posts)

    conn1.commit()

    cursor1.execute("SELECT id FROM users WHERE login='denis'")
    denis_id = cursor1.fetchone()[0]
    cursor1.execute("SELECT id FROM users WHERE login='sofia'")
    sofia_id = cursor1.fetchone()[0]
    cursor1.execute("SELECT id FROM users WHERE login='anna'")
    anna_id = cursor1.fetchone()[0]

    conn1.close()

    conn2 = sqlite3.connect('db2.sqlite')
    cursor2 = conn2.cursor()

    cursor2.execute("SELECT id FROM space_type WHERE name='global'")
    global_id = cursor2.fetchone()[0]
    cursor2.execute("SELECT id FROM space_type WHERE name='blog'")
    blog_id = cursor2.fetchone()[0]
    cursor2.execute("SELECT id FROM space_type WHERE name='post'")
    post_space_id = cursor2.fetchone()[0]

    cursor2.execute("SELECT id FROM event_type WHERE name='login'")
    login_id = cursor2.fetchone()[0]
    cursor2.execute("SELECT id FROM event_type WHERE name='comment'")
    comment_id = cursor2.fetchone()[0]
    cursor2.execute("SELECT id FROM event_type WHERE name='create_post'")
    create_post_id = cursor2.fetchone()[0]
    cursor2.execute("SELECT id FROM event_type WHERE name='logout'")
    logout_id = cursor2.fetchone()[0]

    logs = [
        ('2026-03-15 10:00:00', denis_id, global_id, login_id),
        ('2026-03-15 10:05:00', denis_id, blog_id, create_post_id),
        ('2026-03-15 10:10:00', sofia_id, global_id, login_id),
        ('2026-03-15 10:15:00', sofia_id, post_space_id, comment_id),
        ('2026-03-15 10:20:00', denis_id, global_id, logout_id),
        ('2026-03-15 10:25:00', anna_id, global_id, login_id),
        ('2026-03-15 10:30:00', anna_id, post_space_id, comment_id),
        ('2026-03-15 10:35:00', anna_id, global_id, logout_id),
        ('2026-03-16 11:00:00', denis_id, global_id, login_id),
        ('2026-03-16 11:05:00', denis_id, blog_id, create_post_id),
        ('2026-03-16 11:10:00', sofia_id, global_id, login_id),
        ('2026-03-16 11:15:00', sofia_id, post_space_id, comment_id),
        ('2026-03-16 11:20:00', denis_id, global_id, logout_id),
    ]
    cursor2.executemany('INSERT INTO logs (datetime, user_id, space_type_id, event_type_id) VALUES (?, ?, ?, ?)', logs)

    conn2.commit()
    conn2.close()


if __name__ == "__main__":
    if os.path.exists('db1.sqlite'):
        os.remove('db1.sqlite')
    if os.path.exists('db2.sqlite'):
        os.remove('db2.sqlite')

    setup_db1()
    setup_db2()
    insert_test_data()