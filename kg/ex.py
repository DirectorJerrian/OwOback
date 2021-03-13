#临时测试，没有用
import spacy
string="2019年12月以来，湖北省武汉市陆续发现了多例新型冠状病毒感染引起的以肺部病变为主的新型传染病患者。"

nlp = spacy.load('zh_core_web_sm')
doc=nlp(string)
for tok in doc:
    print(tok.text, "...", tok.dep_)