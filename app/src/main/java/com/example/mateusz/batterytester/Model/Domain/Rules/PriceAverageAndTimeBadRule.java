package com.example.mateusz.batterytester.Model.Domain.Rules;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Interface.IRule;


public class PriceAverageAndTimeBadRule  implements IRule {
    @Override
    public void Run(FuzzyConclusionSet conclusionSet) {
        conclusionSet.set_badRatingValue(Math.min(conclusionSet.PriceAverage,conclusionSet.TimeBad));
    }
}
