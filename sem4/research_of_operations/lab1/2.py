from scipy.optimize import linprog

z = [4, 1]  # 4x1 + x2 -> min

A = [[-4, -3], #4 x1 +3x2 >= 6 (* (-1))
     [1, 2]] # x1 + 2x2 <= 4
B = [-6, 4]

Aeq = [[3, 1]] # 3x1 + x2 = 3
Beq = [3]

ct = [
    (0, None),
    (0, None)
]

res = linprog(c=z, A_ub=A, b_ub=B, A_eq=Aeq, b_eq=Beq, bounds=ct)

print("Решение:" if res.success else "Нет решения:")
print(f'z = {res.fun}')
print(f'[x1, x2] = {res.x}')
