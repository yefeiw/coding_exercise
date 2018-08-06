def maxA(N):
    buffer = 0
    counts = [0, 0, 1, 2]
    for i in range(N -2):
        if counts[-3]*2 > counts[-1] + 1:
            print("buffer", buffer)
            temp = max(counts[-3]*2, counts[-1] + buffer, counts[-1] + 1)
            counts.append(temp)
    return counts[N+1]

print(maxA(6))
print(maxA(7))
print(maxA(13))