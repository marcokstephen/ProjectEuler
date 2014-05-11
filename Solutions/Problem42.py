import math
def hex(n):
    return n*(2*n-1)

def tri(num):
    n = (-1 + math.sqrt(1 + 8*num))/2
    intN = math.floor(n)
    if (intN*(intN + 1)/2 == num):
        return True
    return False

def pent(num):
    n = (1 + math.sqrt(1 + 24*num))/6
    intN = math.floor(n)
    if (intN*(3*intN-1)/2 == num):
        return True
    return False

hexPosition = 144
while(True):
    num = hex(hexPosition)
    if (tri(num) and pent(num)):
        print num
        break
    hexPosition += 1
