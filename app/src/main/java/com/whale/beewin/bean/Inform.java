package com.whale.beewin.bean;

/**
 * 散投收益类
 * Created by Administrator on 2017/3/29 0029.
 */

public class Inform {
    private String month;
    private String corpus;
    private String interest;
    private String corpusSum;
    private String interestSum;
    private String extra;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCorpus() {
        return corpus;
    }

    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getCorpusSum() {
        return corpusSum;
    }

    public void setCorpusSum(String corpusSum) {
        this.corpusSum = corpusSum;
    }

    public String getInterestSum() {
        return interestSum;
    }

    public void setInterestSum(String interestSum) {
        this.interestSum = interestSum;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Inform(String month, String corpus, String interest, String corpusSum, String interestSum, String extra) {
        this.month = month;
        this.corpus = corpus;
        this.interest = interest;
        this.corpusSum = corpusSum;
        this.interestSum = interestSum;
        this.extra = extra;
    }

    public Inform() {
    }
}
