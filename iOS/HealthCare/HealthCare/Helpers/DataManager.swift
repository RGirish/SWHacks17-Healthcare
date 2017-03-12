//
//  DataManager.swift
//  HealthCare
//
//  Created by student on 3/10/17.
//  Copyright © 2017 ASU. All rights reserved.
//

import Foundation
import UIKit

class DataManger{
    //Baed on example from http://www.raywenderlich.com/82706/working-with-json-in-swift-tutorial
    
    class func getDataFromURLWithSuccess(_ apiUrl: String, success:@escaping ((_ appData: Data?) -> Void)) {
        
        loadDataFromURL(URL(string: apiUrl)!, completion:{(data, error) -> Void in
            if let urlData = data {
                success(urlData)
            }
            else
            {
                success(nil)
            }
        })
    }
    
    class func loadDataFromURL(_ url: URL, completion:@escaping (_ data: Data?, _ error: NSError?) -> Void) {
        
        let config = URLSessionConfiguration.default
        let session = URLSession(configuration: config)
        
        let task = session.dataTask(with: url, completionHandler: {
            (data, response, error) in
            
            if let responseError = error {
                completion(nil, responseError as NSError?)
            } else if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode != 200 {
                    let statusError = NSError(domain:"", code:httpResponse.statusCode, userInfo:[NSLocalizedDescriptionKey : "HTTP status code has unexpected value."])
                    completion(nil, statusError)
                } else {
                    completion(data, nil)
                }
            }
            
        })
        task.resume()
    }
}
