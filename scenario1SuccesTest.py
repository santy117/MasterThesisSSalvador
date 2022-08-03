import random

ngramGeom = [3, 1, 2]
ngramOccurrences = [2, 3, 1]
versiones = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
weights_number = [ 3, 3, 3, 1, 1, 1, 1, 1, 1, 1]
numPartidas = 5000
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
