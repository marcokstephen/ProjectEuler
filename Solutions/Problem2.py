sum = 2
firstFib = 1
secondFib = 2
thirdFib = 3
while (thirdFib < 4000000):
    firstFib = secondFib
    secondFib = thirdFib
    thirdFib = firstFib + secondFib
    if (thirdFib%2 == 0 and thirdFib < 4000000):
        sum += thirdFib
print sum
