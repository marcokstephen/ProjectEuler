from math import log10

content = []
with open('p099_base_exp.txt') as f:
    content = f.readlines()

maxNum = 0
counter = 0
maxLineNumber = 0
for line in content:
    counter += 1
    values = line.split(',')
    base = int(values[0])
    exponent = int(values[1])
    if maxNum < (exponent * log10(base)):
        maxNum = exponent * log10(base)
        maxLineNumber = counter

print maxLineNumber
