package com.example.mateusz.batterytester.Model.Service;

import com.example.mateusz.batterytester.Model.Domain.Objects.ArduinoResponse;

public class ArduinoTranslationService {

    private String _rawResponse;

    public ArduinoResponse ProcessResponse() throws Exception {
        try {
            String[] spitedResponse = _rawResponse.split("-");
            if(spitedResponse.length != 6){
                throw new Exception("An unexpected error occurred during parsing message. Not enough parameters to continue.");
            }
            ArduinoResponse response = new ArduinoResponse();

            response.set_temperature(spitedResponse[0].trim());
            response.set_voltage(spitedResponse[1].trim());
            response.set_current(spitedResponse[2].trim());
            response.set_time(spitedResponse[3].trim());
            response.set_capacity(spitedResponse[4].trim());
            response.set_stop(Integer.parseInt(spitedResponse[5].trim()) == 1);
            return response;
        }
        catch (Exception e){
            throw e;
        }
    }

    public void set_rawResponse(String _rawResponse) {
        this._rawResponse = _rawResponse;
    }
}
