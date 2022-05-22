import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from collections import defaultdict
plt.style.use(style='seaborn')
#matplotlib inline

#method to generate n-grams:
#params:
#text-the text for which we have to generate n-grams
#ngram-number of grams to be generated from the text(1,2,3,4 etc., default value=1)

def generate_N_grams(text,ngram=1):
  words=[word for word in text.split(" ")]  
  print("Numbers: ",words)
  temp=zip(*[words[i:] for i in range(0,ngram)])
  ans=[' '.join(ngram) for ngram in temp]
  return ans

#UNIGRAMAS
conteoUnigrama=defaultdict(int)
text = "1 1 1 1 2 2 2 1 2 16 16 16 16 4 3 45 2 12 4 2 2 2 1 1 1 1 4 53 436 34 4 4 4 4 4 1 2 1 12 323 34 6 6 66 634 4 34 34 234 324 324 234 23 423 4 234 235 65 66 6 6 6 23 4 234 234 2 423 43 24 234 1 4 53 436 34 4 4 4 4 4 1 2 1 12 323 34 6 6 6 6 6346 634 4 34 34 234 324 324 234 23 423 4 234 235 65 66 6 6 6 23 4 234 234 2 423 43 24 234 1 4 53 436 34 4 4 4 4 4 1 2 1 12 323 34 6 6 6 6 6344 34 4 344 234 1234 124 336 2664 243 3 1 14 235 65 16 6 1 6 23 4 234 234 222 13 43 21 234 1 4 53 416 34 1 2 1 12 323 34 6 6 6 6 634 4 34 4 344 234 1234 124 336 2664 243 3 1 14 235 65 16 6 1 6 23 4 234 234 222 13 43 21 234 1 4 53 416 34 1 2 1 12 323 34 6 6 6 6 634"
for word in generate_N_grams(text):
    conteoUnigrama[word]+=1

#focus on more frequently occuring numbers
df_conteo=pd.DataFrame(sorted(conteoUnigrama.items(),key=lambda x:x[1],reverse=True))

pd1=df_conteo[0][:10]
pd2=df_conteo[1][:10]

plt.figure(1,figsize=(16,4))
plt.bar(pd1,pd2, color ='green',
        width = 0.4)
plt.xlabel("Object versions reviewed by the user")
plt.ylabel("Count")
plt.title("Top 10 object versions reviewed by user -UNIGRAM ANALYSIS")
plt.savefig("unigram.png")
plt.show()


#BIGRAMAS
conteoBigrama=defaultdict(int)
for word in generate_N_grams(text, 2):
    conteoBigrama[word]+=1

#focus on more frequently occuring numbers
df_conteoBigrama=pd.DataFrame(sorted(conteoBigrama.items(),key=lambda x:x[1],reverse=True))

pd1b=df_conteoBigrama[0][:10]
pd2b=df_conteoBigrama[1][:10]

plt.figure(2,figsize=(16,4))
plt.bar(pd1b,pd2b, color ='green',
        width = 0.4)
plt.xlabel("Object versions reviewed by the user")
plt.ylabel("Count")
plt.title("Top 10 object versions reviewed by user -BIGRAM ANALYSIS")
plt.savefig("bigram.png")
plt.show()

#TRIGRAMAS
conteoTrigrama=defaultdict(int)
for word in generate_N_grams(text, 3):
    conteoTrigrama[word]+=1

#focus on more frequently occuring numbers
df_conteoTrigrama=pd.DataFrame(sorted(conteoTrigrama.items(),key=lambda x:x[1],reverse=True))

pd1t=df_conteoTrigrama[0][:10]
pd2t=df_conteoTrigrama[1][:10]

plt.figure(3,figsize=(16,4))
plt.bar(pd1t,pd2t, color ='green',
        width = 0.4)
plt.xlabel("Object versions reviewed by the user")
plt.ylabel("Count")
plt.title("Top 10 object versions reviewed by user -TRIGRAM ANALYSIS")
plt.savefig("trigram.png")
plt.show()