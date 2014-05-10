import itertools
import math
#if sum of digits is divisble by 3, entire num is divisible by 3 (not prime)
list7 = list(itertools.permutations([7,6,5,4,3,2,1]))
list4 = list(itertools.permutations([4,3,2,1]))
pandigitalList = list7+list4

def isPrime(n):
    max = math.sqrt(n)
    currentTest = 2
    while (currentTest <= max):
        if (n%currentTest == 0):
            return False
        currentTest += 1
    return True

def tupleToNum(tup):
    length = len(tup)
    output = 0;
    for x in range (0,length):
        output *= 10
        output += tup[x]
    return output
        
while pandigitalList:
    first = pandigitalList.pop(0)
    num = tupleToNum(first)
    if (isPrime(num)):
        print num
        break