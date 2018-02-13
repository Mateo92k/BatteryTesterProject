package com.example.mateusz.batterytester.Model.Domain;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Interface.IRule;

/**
 * Created by Sebas on 13.02.2018.
 */

public class PriceExpensiveAndTImeVeryBad implements IRule {
    @Override
    public void Run(FuzzyConclusionSet conclusionSet) {
        conclusionSet.set_veryBadRatingValue(Math.min(conclusionSet.PriceExpensive,conclusionSet.TimeVeryBad));
    }
}
