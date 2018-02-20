package com.example.mateusz.batterytester.Model.Domain;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Interface.IRule;


public class PriceAverageAndTimeGoodRule implements IRule {
    @Override
    public void Run(FuzzyConclusionSet conclusionSet) {
        conclusionSet.set_goodRatingValue(Math.min(conclusionSet.PriceAverage,conclusionSet.TimeGood));
    }
}
