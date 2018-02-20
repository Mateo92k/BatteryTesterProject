package com.example.mateusz.batterytester.Model.Interface;

import com.example.mateusz.batterytester.Model.Domain.Objects.FuzzyConclusionSet;


public interface IRule {
    void Run(FuzzyConclusionSet conclusionSet);
}
