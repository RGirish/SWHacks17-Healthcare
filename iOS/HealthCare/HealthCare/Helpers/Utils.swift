//
//  Utils.swift
//  HealthCare
//
//  Created by student on 3/10/17.
//  Copyright © 2017 ASU. All rights reserved.
//

import Foundation

class Utils
{
    class func getURLWithString(url:String) -> String
    {
        return urlWithName(url)
    }
    
    fileprivate class func urlWithName(_ name: String) -> String
    {
        let baseURL = "http://10.144.188.118:8080/",
        absolutePath = baseURL + name
        return absolutePath
    }
}


