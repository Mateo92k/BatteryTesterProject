package com.example.mateusz.batterytester.Model.Service;

import com.example.mateusz.batterytester.Model.Domain.Fuzzy;
import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceAverageAndTimeBadRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceAverageAndTimeGoodRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceAverageAndTimeVeryBadRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceAverageAndTimeVeryGoodRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceCheapAndTImeGoodRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceCheapAndTImeVeryBadRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceCheapAndTImeVeryGoodRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceCheapAndTimeBadRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceExpensiveAndTImeBadRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceExpensiveAndTImeGoodRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceExpensiveAndTImeVeryBadRule;
import com.example.mateusz.batterytester.Model.Domain.Rules.PriceExpensiveAndTImeVeryGoodRule;
import com.example.mateusz.batterytester.Model.Interface.IRule;

import java.util.ArrayList;


public class RatingService {

    public double RateBattery(double price, double time){

        Fuzzy fuzzy = new Fuzzy();
        FuzzyConclusionSet conclusionSet = new FuzzyConclusionSet();

        conclusionSet.PriceCheap = fuzzy.CalculateValueByUseFullTrapeze(0,0,1,1.5,price);
        conclusionSet.PriceAverage = fuzzy.CalculateValueByUseFullTrapeze(1,1.5,2.5,3,price);
        conclusionSet.PriceExpensive = fuzzy.CalculateValueByUseFullTrapeze(2.5,3,30,30,price);

        conclusionSet.TimeVeryBad = fuzzy.CalculateValueByUseFullTrapeze(0,0, 20, 50,time);
        conclusionSet.TimeBad = fuzzy.CalculateValueByUseFullTrapeze(20,50,90,105,time);
        conclusionSet.TimeGood = fuzzy.CalculateValueByUseFullTrapeze(90,105,115,130,time);
        conclusionSet.TimeVeryGood = fuzzy.CalculateValueByUseFullTrapeze(115,130,240,240,time);


        ArrayList<IRule> rules = GetAvailableRules();
        for (IRule rule: rules) {
            rule.Run(conclusionSet);
        }

        return fuzzy.GetFinalConclusionByUsingWeightedAverageMethod(conclusionSet);
    }



    private ArrayList<IRule> GetAvailableRules(){
        ArrayList<IRule> rules = new ArrayList<>();

        rules.add(new PriceAverageAndTimeBadRule());
        rules.add(new PriceAverageAndTimeGoodRule());
        rules.add(new PriceAverageAndTimeVeryBadRule());
        rules.add(new PriceAverageAndTimeVeryGoodRule());
        rules.add(new PriceCheapAndTimeBadRule());
        rules.add(new PriceCheapAndTImeGoodRule());
        rules.add(new PriceCheapAndTImeVeryBadRule());
        rules.add(new PriceCheapAndTImeVeryGoodRule());
        rules.add(new PriceExpensiveAndTImeBadRule());
        rules.add(new PriceExpensiveAndTImeGoodRule());
        rules.add(new PriceExpensiveAndTImeVeryBadRule());
        rules.add(new PriceExpensiveAndTImeVeryGoodRule());
        return rules;
    }
}
