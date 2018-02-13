package com.example.mateusz.batterytester.Model.Interface;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;

/**
 * Created by Sebas on 13.02.2018.
 */

public interface IRule {
    void Run(FuzzyConclusionSet conclusionSet);
}
