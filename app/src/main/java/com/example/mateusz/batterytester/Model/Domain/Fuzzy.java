package com.example.mateusz.batterytester.Model.Domain;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;


public class Fuzzy {
    public double CalculateValueByUseFullTrapeze(double leftSlopeStart, double leftSlopeEnd,double rightSlopeStart, double rightSlopeEnd, double value){

        double result = 0.0;
        if(value <= leftSlopeStart || value >= rightSlopeEnd){
            return result;
        }

        if(value < leftSlopeEnd){
            return (value - leftSlopeStart)/(leftSlopeEnd - leftSlopeStart);
        }

        if(value > rightSlopeStart){
            return (rightSlopeEnd - value)/(rightSlopeEnd - rightSlopeStart);
        }

        return 1.0;
    }

    public double GetFinalConclusionByUsingWeightedAverageMethod(FuzzyConclusionSet conclusionSet){
        return (conclusionSet.get_veryBadRatingValue() * conclusionSet.get_veryBadRatingSingletonValue() +
                conclusionSet.get_badRatingValue() * conclusionSet.get_badRatingSingletonValue() +
                conclusionSet.get_averageRatingValue() * conclusionSet.get_averageRatingSingletonValue() +
                conclusionSet.get_goodRatingValue() * conclusionSet.get_goodRatingSingletonValue() +
                conclusionSet.get_veryGoodRatingValue() * conclusionSet.get_veryGoodRatingSingletonValue()) /
                (conclusionSet.get_veryBadRatingValue() +
                        conclusionSet.get_badRatingValue() +
                        conclusionSet.get_averageRatingValue() +
                        conclusionSet.get_goodRatingValue() +
                        conclusionSet.get_veryGoodRatingValue());
    }
}
