import spacy
from spacy.matcher.matcher import Matcher

#抽提句子关系，，V
def get_relation(sent,language):
    if language=="zh":
        nlp = spacy.load('zh_core_web_sm')
    else:
        nlp = spacy.load('en_core_web_sm')
    doc = nlp(sent)


 # Matcher class object
    matcher = Matcher(nlp.vocab)


 #define the pattern
    pattern = [{'DEP':'ROOT'},
            {'DEP':'prep','OP':"?"},
            {'DEP':'agent','OP':"?"},
            {'POS':'ADJ','OP':"?"}]


    matcher.add("matching_1", None, pattern)


    matches = matcher(doc)
    k = len(matches) - 1


    span = doc[matches[k][1]:matches[k][2]]


    return(span.text)

#print(get_relation("2019 年 12 月以来，湖北省武汉市陆续发现了多例新型冠状病毒感染引起的以肺部病变为主的新型传染病患者。","zh"))