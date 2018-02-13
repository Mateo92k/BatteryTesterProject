package com.example.mateusz.batterytester.Model.Domain;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Interface.IRule;

/**
 * Created by Sebas on 13.02.2018.
 */

public class PriceAverageAndTimeVeryGoodRule implements IRule {
    @Override
    public void Run(FuzzyConclusionSet conclusionSet) {
        conclusionSet.set_veryGoodRatingValue(Math.min(conclusionSet.PriceAverage,conclusionSet.TimeVeryGood));
    }
}
