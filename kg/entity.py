import spacy

#该实体抽取只适合于英文
def get_entities(sent,language):
    ## chunk 1
    # 我在这个块中定义了一些空变量。prv tok dep和prv tok text将分别保留句子中前一个单词和前一个单词本身的依赖标签。前缀和修饰符将保存与主题或对象相关的文本。
    ent1 = ""
    ent2 = ""

    interval=""
    if language=="zh":
        nlp = spacy.load('zh_core_web_sm')
        #该方法不准确
        # doc = nlp(sent)
        # subbool=True
        # objbool=True
        # for tok in doc:
        #     if tok.dep_=="nsubj" and subbool:
        #         ent1=tok.text
        #         subbool=False
        #     elif tok.dep_=="dobj" and objbool:
        #         ent2=tok.text
        #         objbool=False
        # return [ent1.strip(), ent2.strip()]

    else:
        nlp = spacy.load('en_core_web_sm')
        interval=" "

    prv_tok_dep = ""  # dependency tag of previous token in the sentence
    prv_tok_text = ""  # previous token in the sentence

    prefix = ""
    modifier = ""

    #############################################################

    for tok in nlp(sent):
        ## chunk 2
        # 接下来，我们将遍历句子中的记号。我们将首先检查标记是否为标点符号。如果是，那么我们将忽略它并转移到下一个令牌。如果标记是复合单词的一部分(dependency tag = compound)，我们将把它保存在prefix变量中。复合词是由多个单词组成一个具有新含义的单词(例如“Football Stadium”, “animal lover”)。
        # 当我们在句子中遇到主语或宾语时，我们会加上这个前缀。我们将对修饰语做同样的事情，例如“nice shirt”, “big house”

        # if token is a punctuation mark then move on to the next token
        if tok.dep_ != "punct":
            # check: token is a compound word or not
            if tok.dep_ == "compound":
                prefix = tok.text
                # if the previous word was also a 'compound' then add the current word to it
                if prv_tok_dep == "compound":
                    prefix = prv_tok_text + interval + tok.text

            # check: token is a modifier or not
            if tok.dep_.endswith("mod") == True:
                modifier = tok.text
                # if the previous word was also a 'compound' then add the current word to it
                if prv_tok_dep == "compound":
                    modifier = prv_tok_text + interval + tok.text

            ## chunk 3
            # 在这里，如果令牌是主语，那么它将作为ent1变量中的第一个实体被捕获。变量如前缀，修饰符，prv tok dep，和prv tok文本将被重置。
            if tok.dep_.find("subj") == True:
                ent1 = modifier + interval + prefix + interval + tok.text
                prefix = ""
                modifier = ""
                prv_tok_dep = ""
                prv_tok_text = ""

                ## chunk 4
            # 在这里，如果令牌是宾语，那么它将被捕获为ent2变量中的第二个实体。变量，如前缀，修饰符，prv tok dep，和prv tok文本将再次被重置。
            if tok.dep_.find("obj") == True:
                ent2 = modifier + interval + prefix + interval + tok.text

            ## chunk 5
            # 一旦我们捕获了句子中的主语和宾语，我们将更新前面的标记和它的依赖标记。
            # update variables
            prv_tok_dep = tok.dep_
            prv_tok_text = tok.text
    #############################################################

    return [ent1.strip(), ent2.strip()]

#print(get_entities("2019年12月以来，湖北省武汉市陆续发现了多例新型冠状病毒感染引起的以肺部病变为主的新型传染病患者。","zh"))