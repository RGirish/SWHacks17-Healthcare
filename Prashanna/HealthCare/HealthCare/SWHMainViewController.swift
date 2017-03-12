//
//  ViewController.swift
//  HealthCare
//
//  Created by student on 3/10/17.
//  Copyright © 2017 ASU. All rights reserved.
//

import UIKit
import Speech

class SWHMainViewController: UIViewController, SFSpeechRecognizerDelegate,UITextViewDelegate {
    
    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var microphoneButton: UIButton!
    
    private let speechRecognizer = SFSpeechRecognizer(locale: Locale.init(identifier: "en-US"))!
    
    private var recognitionRequest: SFSpeechAudioBufferRecognitionRequest?
    private var recognitionTask: SFSpeechRecognitionTask?
    private let audioEngine = AVAudioEngine()
    
    private let diff = "Differential Diagnosis, people.. "
    private var recognizedText:String?
    private var reachability: Reachability? = Reachability.networkReachabilityForInternetConnection()
    private var conditionName: String? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.hideKeyboardWhenTappedAround()
        
        microphoneButton.isEnabled = false
        
        speechRecognizer.delegate = self
        
        SFSpeechRecognizer.requestAuthorization { (authStatus) in
            
            var isButtonEnabled = false
            
            switch authStatus {
            case .authorized:
                isButtonEnabled = true
                
            case .denied:
                isButtonEnabled = false
                print("User denied access to speech recognition")
                
            case .restricted:
                isButtonEnabled = false
                print("Speech recognition restricted on this device")
                
            case .notDetermined:
                isButtonEnabled = false
                print("Speech recognition not yet authorized")
            }
            
            OperationQueue.main.addOperation() {
                self.microphoneButton.isEnabled = isButtonEnabled
            }
        }
    }
    
    private func checkReachability() -> Bool {
        guard let r = reachability else { return false }
        if r.isReachable  {
            return true
        } else {
            return false
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        
        if segue.identifier == "Details" {
            
            let detailsViewController = segue.destination as! DetailsViewController
            if let name = self.conditionName
            {
                detailsViewController.titleString = name
            }
            else
            {
                detailsViewController.titleString = ""
            }
        }
        
    }
    
    private func callWebService(symptom: String)
    {
         let apiUrl: String = "http://192.168.0.6:8080/getDiagnosis?symptoms="+symptom+"&sex=male&age=30"

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
                    print(json)
                    
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
                    
                    DispatchQueue.main.async {
                        self.performSegue(withIdentifier: "Details", sender: self)
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
    
    @IBAction func microphoneTapped(_ sender: AnyObject) {
        if audioEngine.isRunning {
            audioEngine.stop()
            recognitionRequest?.endAudio()
            microphoneButton.isEnabled = false
            
            let image : UIImage = UIImage(named:"ready")!
            microphoneButton.setImage(image, for: .normal)
            
//            callWebService()
            
//            if let text = self.recognizedText
//            {
//                callWebService(symptom: text)
//            }

            if self.recognizedText != nil
            {
                callWebService(symptom: self.recognizedText!)
            }
            
        } else {
            startRecording()
            
            let image : UIImage = UIImage(named:"recording")!
            microphoneButton.setImage(image, for: .normal)
        }
    }
    
    func startRecording() {
        
        if recognitionTask != nil {  //1
            recognitionTask?.cancel()
            recognitionTask = nil
        }
        
        let audioSession = AVAudioSession.sharedInstance()  //2
        do {
            try audioSession.setCategory(AVAudioSessionCategoryRecord)
            try audioSession.setMode(AVAudioSessionModeMeasurement)
            try audioSession.setActive(true, with: .notifyOthersOnDeactivation)
        } catch {
            print("audioSession properties weren't set because of an error.")
        }
        
        recognitionRequest = SFSpeechAudioBufferRecognitionRequest()  //3
        
        guard let inputNode = audioEngine.inputNode else {
            fatalError("Audio engine has no input node")
        }  //4
        
        guard let recognitionRequest = recognitionRequest else {
            fatalError("Unable to create an SFSpeechAudioBufferRecognitionRequest object")
        } //5
        
        recognitionRequest.shouldReportPartialResults = true  //6
        
//        var symptomSet : Set<String> = []
        recognitionTask = speechRecognizer.recognitionTask(with: recognitionRequest, resultHandler: { (result, error) in  //7
            
            var isFinal = false  //8
            
            if result != nil {
                
//                var tempSymptomSet : Set<String> = []
//                
//                if symptomSet.count == 0
//                {
//                    let currentString = (result?.bestTranscription.formattedString)!
//                    symptomSet.insert(currentString)
//                }
//                
//                else if symptomSet.count > 0{
//                    
//                    var currentString = (result?.bestTranscription.formattedString)!
//                    
//                    for symptom in symptomSet
//                    {
//                        if currentString.range(of: symptom) != nil{
//                            currentString = (currentString as NSString).replacingOccurrences(of: symptom, with: "")
//                        }
//                    }
//                    
//                    tempSymptomSet.insert(currentString)
//                }
//                
//                if(tempSymptomSet.count > 0)
//                {
//                    for symptom in tempSymptomSet
//                    {
//                        symptomSet.insert(symptom)
//                    }
//                    
//                    tempSymptomSet = []
//                }
//                
//                var string = ""
//                for symptom in symptomSet
//                {
//                    if string != ""
//                    {
//                        string = string+","+symptom
//                    }
//                    
//                    else
//                    {
//                        string = symptom
//                    }
//                }
//                
////                let replaced = (string as NSString).replacingOccurrences(of:"Test", with: "")
//                
//                self.textView.text = self.diff + " with symptoms of " + string + " and it's not lupus but could be one of the suspects... Go... "  //9
//                self.recognizedText = string;
                
                self.recognizedText = (result?.bestTranscription.formattedString)!
                
                self.textView.text = self.diff + " with possible symptoms of " + (result?.bestTranscription.formattedString)! + " and it's not lupus but one of the suspects... Go... "  //9
                
                isFinal = (result?.isFinal)!
            }
            
            if error != nil || isFinal {  //10
                self.audioEngine.stop()
                inputNode.removeTap(onBus: 0)
                
                self.recognitionRequest = nil
                self.recognitionTask = nil
                
                self.microphoneButton.isEnabled = true
            }
        })
        
        let recordingFormat = inputNode.outputFormat(forBus: 0)  //11
        inputNode.installTap(onBus: 0, bufferSize: 1024, format: recordingFormat) { (buffer, when) in
            self.recognitionRequest?.append(buffer)
        }
        
        audioEngine.prepare()  //12
        
        do {
            try audioEngine.start()
        } catch {
            print("audioEngine couldn't start because of an error.")
        }
        
        textView.text = diff
        
    }
    
    func speechRecognizer(_ speechRecognizer: SFSpeechRecognizer, availabilityDidChange available: Bool) {
        if available {
            microphoneButton.isEnabled = true
        } else {
            microphoneButton.isEnabled = false
        }
    }
    
    func textViewDidEndEditing(_ textView: UITextView) {
        textView.resignFirstResponder();
    }
}

extension UIViewController {
    func hideKeyboardWhenTappedAround() {
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
    }
    
    func dismissKeyboard() {
        view.endEditing(true)
    }
}

