import { Injectable, AfterViewInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { Card } from '../card';

import { Observable } from 'rxjs';

declare var google: any;

@Injectable({
  providedIn: 'root'
})
export class ClientsService implements AfterViewInit {

  loggedIn = false;
  accessToken: String = '';
  email: String = '';
  apiKey: String = 'AIzaSyCzbVg-JhZ5enrOtt6KwzDFqG9_7C-vSYo'; /*Your API Key*/
  geocoder: any;

  ngAfterViewInit()
  {
  }

  constructor(private http: HttpClient, public alertController: AlertController, private router: Router) 
  {
  }

  register(credentials) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
       // 'Access-Control-Allow-Origin':'*'
      })
    };

    this.http.post('http://localhost:8298/account-management/register', credentials, httpOptions)

    .subscribe(data => {
      console.log(data['message']);
      this.presentWarning('Account registered successfully!',
       'Welcome to DLivr! Please follow the confirmation link you received at your email address to finish validating your account!');
      this.login(credentials);
     }, error => {
      console.log(error);
      this.presentWarning('Atentie!', error.error['message']);
    });
  }

  login(credentials) {

    console.log(credentials);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };

    this.http.post('http://localhost:8298/account-management/login', credentials, httpOptions)
    .subscribe(data => {
      this.accessToken = data['token'];
      this.email = JSON.parse(credentials)['email'];
      this.loggedIn = true;
      
      console.log('Access token received:' + this.accessToken);
      console.log('Email received:' + this.email);
      
      this.router.navigateByUrl('app/menu/home');
     }, error => {
      this.presentWarning('Atentie!', error.error['message']);
    });
  }

  sendPackageRating(id, rating)
  {
    const body = {
      'idPackage': id, 
      'rating': rating
    };

    console.log("sendPackageRating: " + JSON.stringify(body));

    return this.http.post(
      'http://localhost:8298/rating-management/rating/setRating', 
      JSON.stringify(body),
      this.makeAuthorizedHeader()
    );
  }

  // async presentRating()
  // {
  //   const alert = await this.alertController.create({
  //     message: '<rating [rate]="rate"' +
  //       'readonly="false"' +
  //       'size="default" ' +
  //       '(rateChange)="onRateChange($event)">' +
  //     '</rating>',
  //     buttons: ['OK']
  //   });
  //   await alert.present();
  // }

  // coroutines
  async presentWarning(hd: String, msg: String) {
   console.log('ms ul asta e ' + msg);
    const alert = await this.alertController.create({
      header: hd.toString(),
      subHeader: '',
      message:
   //   "" + msg,
  msg.toString(),
      buttons: ['OK']
    });

    await alert.present();
  }

  makeAuthorizedHeader() 
  {
    return {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + this.accessToken
      })
    };
  }

  // GET for mypackages  
  getPackages()
  {
    return this.http.get('http://localhost:8298/package-management/packages/getPackagesSender', this.makeAuthorizedHeader());
  }

  // validateEmailAddress(email)
  // {
  //   const mapOptions = {
  //       center: {lat: 47.143022, lng: 27.581259},
  //       zoom: 15,
  //       mapTypeControl: false
  //   };

  //   const script = document.createElement('script');
  //   script.src = 'https://maps.googleapis.com/maps/api/js?key=' + this.apiKey;
  //   script.id = 'googleMap';
  //   script.type = 'text/javascript';
  //   console.log(script.src);

  //   document.head.appendChild(script);
  //   this.geocoder = new google.maps.Geocoder();

  //   return this.geocoder.geocode({'address': '10389 Shenandoah'}, function(results, status)
  //   {
  //     console.log(status);
  //   });
  // }

  validateAddress()
  {
    var address1 = document.getElementById('pickupAddressInput');
    //var address2 = document.getElementById('deliveryAddressInput');

    this.geocoder = new google.maps.Geocoder();
    this.geocoder.geocode({'address': address1}, function(results, status) {
      if (status === 'OK') 
      {
        console.log("Address is good.");
      }
      else 
      {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }

  truncateEmailHost(email) : String{
    var index = email.lastIndexOf('@');
    return email.substring(0, index);
  }

  // POST for mypackages
  addPackage(newPackage)
  {
    const body = {
      "emailSender" : this.email,
      "namePackage" : newPackage['namePackage'],
      "senderAdress": newPackage['senderAdress'],
      "receiverAdress": newPackage['receiverAdress'],
      "kilograms": newPackage['kilograms'],
      "phoneNumberSender": newPackage['phoneNumberSender'],
      "phoneNumberReceiver": newPackage['phoneNumberReceiver'],
      "receiverName": newPackage['receiverName'],
      "senderName": newPackage['senderName'],
      "length": newPackage['length'],
      "width": newPackage['width'],
      "height": newPackage['height']
    };
    
    console.log(body);
    return this.http.post('http://localhost:8298/package-management/packages/registerPackage', 
      JSON.stringify(body), 
      this.makeAuthorizedHeader()
    );
  }

  // PUT localhost:8298/package-management/packages/modifyPackageInformations modifica informatiile despre un pachet (request facut de sender). 
  // Primeste in body :  id,namePackage,senderAddress,receiverAddress, 
  // phoneNumberSender,phoneNumberReceiver,receiverName,kilograms,length,width,height. Numai id-ul este obligatoriu, celelalte campuri pot fi null

  deletePackage(id)
  {
    return this.http.delete('http://localhost:8298/package-management/packages/deletePackage/' + id, this.makeAuthorizedHeader());
  }

  // PUT for mypackages
  editPackage(packageToUpdate)
  {
    return this.http.put('http://localhost:8298/package-management/packages/modifyPackageInformations', 
      packageToUpdate, 
      this.makeAuthorizedHeader()
    );
  }

  mypackagesdriverget()
  {
   console.log('Access email MY PACK DRIVER ' + this.email);
   //console.log('Acces token MY PACK DRIVER ' + data['email']);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':'Bearer ' + this.accessToken
      })  
    };
      
    return this.http.get('http://localhost:8298/package-management/packages/getPackagesDriver',httpOptions)

  }

  getCards()
  {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':'Bearer ' + this.accessToken
      })  
      };
      
    return this.http.get('http://localhost:8298/account-management/accountManagement/getCards',httpOptions)

  }

  addCard(card: Card){

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':'Bearer ' + this.accessToken
      })  
      };

    return this.http.post<any>('http://localhost:8298/account-management/accountManagement/addCard', card, httpOptions )
  }


  deleteCard(card: number) {
    console.log('Delete this card number: ' + card);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':'Bearer ' + this.accessToken,
        'Access-Control-Allow-Origin': '*'

      })  
      };

    return this.http.delete('http://localhost:8298/account-management/accountManagement/deleteCard/'+ card, httpOptions )
    
  }

  getPackagesInAreaOf(location: String) {
    return this.http.get('http://localhost:8298/package-management/packages/getPackages/'
    + location.toString(), this.makeAuthorizedHeader());
  }
}