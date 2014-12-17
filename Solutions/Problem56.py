def sumDigits(number):
    sumOfDigits = 0
    while (number != 0):
        sumOfDigits += (number%10)
        number = (int)(number/10)
    return sumOfDigits

maxSum = 0
for a in range (0,100):
    for b in range (0,100):
        expValue = a**b
        if (sumDigits(expValue) > maxSum):
            maxSum = sumDigits(expValue)

print maxSum
