def xor(a, b, c, d):
    return str((a + b + c + d).count("1") % 2)


print("Введите подряд 7 цифр (0 или 1):")
number = input()
while (number.count("0") + number.count("1")) != 7:
    print("Неправильный ввод, попробуйте ещё раз:")
    number = input()

bits = ["r1", "r2", "i1", "r3", "i2", "i3", "i4"]

step2 = []
step = 0
while 2 ** step < len(number) + 1:
    step2.append(2 ** step)
    step += 1

i = [number[x - 1] for x in range(1, len(number) + 1) if x not in step2]
r = [number[x - 1] for x in range(1, len(number) + 1) if x in step2]
s1 = xor(r[0], i[0], i[1], i[3])
s2 = xor(r[1], i[0], i[2], i[3])
s3 = xor(r[2], i[1], i[2], i[3])
s = s1 + s2 + s3
num = int(s[::-1], 2)

if num == 0:
    print("Ошибок нет")
    r_number = number
else:
    print("Ошибка в бите:", bits[num - 1])
    r_number = number[:num - 1] + str(int((not int(number[num - 1])))) + number[num:]
outp = ''
for j in range(1, 8):
    if j not in step2:
        outp += r_number[j - 1]
print(outp)