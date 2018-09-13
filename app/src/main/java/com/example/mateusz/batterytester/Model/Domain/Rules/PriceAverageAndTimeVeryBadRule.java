package com.example.mateusz.batterytester.Model.Domain.Rules;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Interface.IRule;


public class PriceAverageAndTimeVeryBadRule implements IRule {
    @Override
    public void Run(FuzzyConclusionSet conclusionSet) {
    conclusionSet.set_veryBadRatingValue(Math.min(conclusionSet.PriceAverage,conclusionSet.TimeVeryBad));
    }
}
