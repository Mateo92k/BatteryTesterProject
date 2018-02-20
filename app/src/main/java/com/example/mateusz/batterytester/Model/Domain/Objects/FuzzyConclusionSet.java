package com.example.mateusz.batterytester.Model.Domain.Objects;


public class FuzzyConclusionSet {

    public double PriceCheap;
    public double PriceAverage;
    public double PriceExpensive;

    public double TimeVeryBad;
    public double TimeBad;
    public double TimeGood;
    public double TimeVeryGood;

    private double _veryBadRatingValue;
    private double _badRatingValue;
    private double _averageRatingValue;
    private double _goodRatingValue;
    private double _veryGoodRatingValue;


    public int get_veryBadRatingSingletonValue(){
        return 1;
    }

    public int get_badRatingSingletonValue(){
        return 2;
    }

    public int get_averageRatingSingletonValue(){
        return 3;
    }

    public int get_goodRatingSingletonValue(){
        return 4;
    }

    public int get_veryGoodRatingSingletonValue(){
        return 5;
    }



    public double get_veryBadRatingValue() {
        return _veryBadRatingValue;
    }

    public void set_veryBadRatingValue(double _veryBadRatingValue) {
        if(this._veryBadRatingValue < _veryBadRatingValue){
            this._veryBadRatingValue = _veryBadRatingValue;
        }
    }

    public double get_badRatingValue() {
        return _badRatingValue;
    }

    public void set_badRatingValue(double _badRatingValue) {
        if(this._badRatingValue < _badRatingValue){
            this._badRatingValue = _badRatingValue;
        }
    }

    public double get_averageRatingValue() {
        return _averageRatingValue;
    }

    public void set_averageRatingValue(double _averageRatingValue) {
        if(  this._averageRatingValue < _averageRatingValue){
            this._averageRatingValue = _averageRatingValue;
        }
    }

    public double get_goodRatingValue() {
        return _goodRatingValue;
    }

    public void set_goodRatingValue(double _goodRatingValue) {
        if(this._goodRatingValue < _goodRatingValue){
            this._goodRatingValue = _goodRatingValue;
        }
    }

    public double get_veryGoodRatingValue() {
        return _veryGoodRatingValue;
    }

    public void set_veryGoodRatingValue(double _veryGoodRatingValue) {
        if(this._veryGoodRatingValue < _veryGoodRatingValue){
            this._veryGoodRatingValue = _veryGoodRatingValue;
        }
    }
}
