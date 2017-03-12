//
//  DetailsViewController.swift
//  HealthCare
//
//  Created by student on 3/11/17.
//  Copyright © 2017 ASU. All rights reserved.
//

import UIKit

class DetailsViewController: UIViewController {

    @IBOutlet weak var textView: UITextView!
    var conditionName: String? = nil
    var titleString:String?
    
    private var reachability: Reachability? = Reachability.networkReachabilityForInternetConnection()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = titleString
        callWebService()
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
        if let name = self.titleString
        {
            let originalUrl = "http://10.142.201.242:8080/getDoctorsAndInsurance?condition="+name+"&location=33.4177513,-111.934767&"
//            let originalUrl = Utils.getURLWithString(url: "getDoctorsAndInsurance?condition="+name+"&location=33.4177513,-111.934767&") as NSString
            let apiUrl :String = originalUrl.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!
            
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
                        
                        let json = JSON(data: apiData!)
//                        print(json)
                        
                        if let conditionsArray = json["conditions"].array{
                            let count = 0
                            for condition in conditionsArray {
                                if(count == 0)
                                {
                                    let name: String? = condition["name"].stringValue
                                    self.conditionName = name;
                                }
                            }
                        }
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
