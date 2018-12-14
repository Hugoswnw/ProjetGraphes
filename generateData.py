import sys
from random import randint

if(sys.argv < 3):
    print("Usage : python"+sys.argv[0]+" <columns> <lines>")

columns = sys.argv[1]
lines = sys.argv[2]
print(columns)
print(lines)

for i in range(0,int(columns)):
    line = ""
    for j in range(0,int(lines)):
        line += str(randint(0, 9))+","+str(randint(0, 9))+"  "
    print(line)
print(".")
