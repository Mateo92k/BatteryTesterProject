package com.example.mateusz.batterytester.Model.Domain;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Interface.IRule;


public class PriceCheapAndTImeVeryGoodRule implements IRule {
    @Override
    public void Run(FuzzyConclusionSet conclusionSet) {
        conclusionSet.set_veryGoodRatingValue(Math.min(conclusionSet.PriceCheap,conclusionSet.TimeVeryGood));
    }
}
