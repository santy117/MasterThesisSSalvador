import random

ngramGeom = [2, 8, 3]
ngramOccurrences = [3, 9, 11]
versiones = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
21, 22, 23, 24, 24, 26, 27, 28, 29, 30]
weights_number = [ 3, 3, 3, 3, 3, 3, 3, 3, 3, 1,
1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
1, 1, 1, 1, 1, 1, 1, 1, 1, 1,]
numPartidas = 50000
successGeom = 0
successOccurrences = 0

for i in range(numPartidas):
    idVersion = random.choices(versiones, weights=weights_number, k=1)
    if idVersion[0] in ngramGeom:
        successGeom = successGeom + 1
    if idVersion[0] in ngramOccurrences:
        successOccurrences = successOccurrences + 1
effectivityGeom = successGeom / numPartidas
effectivityOccurrences = successOccurrences / numPartidas


print("Efectivity geometric distribution: "+ str(effectivityGeom))
print("Efectivity number occurrences: "+ str(effectivityOccurrences))
