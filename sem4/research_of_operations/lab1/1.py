from scipy.optimize import linprog

z = [4, -1, 1] # 4x1 - x2 + x3 -> min

A = [[2, 3, -3]] # -2x1 - 3x2 + 3x3 >= -1 (*(-1))
B = [1]

Aeq = [[-4, 1, -2], #-4x1 + x2 - 2x3 = -3
       [5, 2, 4]] # 5x1 + 2x2 + 4x3 = 2
Beq = [-3, 2]

ct = [
    (0, None),
    (0, None),
    (0, None),

]

res = linprog(c=z, A_ub=A, b_ub=B, A_eq=Aeq, b_eq=Beq, bounds=ct)

print("Решение:" if res.success else "Нет решения:")
print(f'z = {res.fun}')
print(f'[x1, x2, x3, x4, x5] = {res.x}')
