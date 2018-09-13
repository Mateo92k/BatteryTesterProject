package com.example.mateusz.batterytester.Model.Domain.Rules;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Interface.IRule;


public class PriceExpensiveAndTImeGoodRule implements IRule {
    @Override
    public void Run(FuzzyConclusionSet conclusionSet) {
        conclusionSet.set_averageRatingValue(Math.min(conclusionSet.PriceExpensive,conclusionSet.TimeGood));
    }
}
