//
//  DetailsViewController.swift
//  HealthCare
//
//  Created by student on 3/11/17.
//  Copyright © 2017 ASU. All rights reserved.
//

import UIKit

class DetailsViewController: UIViewController {

    var conditionName: String? = nil
    var titleString:String?
    
    private var reachability: Reachability? = Reachability.networkReachabilityForInternetConnection()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = titleString
//        callWebService()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    private func checkReachability() -> Bool {
        guard let r = reachability else { return false }
        if r.isReachable  {
            return true
        } else {
            return false
        }
    }
    
    private func callWebService()
    {
        let apiUrl: String = "http://192.168.0.6:8080/getDoctorsAndInsurance?condition=Tension-type%20headaches&location=33.4177513,-111.934767&"
        
        if checkReachability()
        {
            DataManger.getDataFromURLWithSuccess(apiUrl, success: { (apiData) in
                if(apiData == nil)
                {
                    let alert = UIAlertController(title: "Something went wrong", message: "Please try again", preferredStyle: UIAlertControllerStyle.alert)
                    alert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: { action in
                        self.dismiss(animated: true, completion: nil)
                    }))
                    self.present(alert, animated: true, completion: nil)
                }
                    
                else
                {
                   
                }
                
            })
        }
            
        else
        {
            let alert = UIAlertController(title: "Check Data Connection", message: "Not able to load data", preferredStyle: UIAlertControllerStyle.alert)
            alert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: { action in
                self.dismiss(animated: true, completion: nil)
                //                self.popToRoot()
            }))
            self.present(alert, animated: true, completion: nil)
            
        }
        
    }

    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        
        let vc = segue.destination as! DetailsViewController;
        vc.title = "Test";
        
    }

}
