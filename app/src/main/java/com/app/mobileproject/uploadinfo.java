package com.app.mobileproject;

public class uploadinfo {


         public String imageName;
         public String imagePrice;
         public String imageStorage;
         public String imageSpecification;
         public String imageURL;
    public uploadinfo() { }

        public uploadinfo(String name,String price,String storage,String specification, String url) {
            this.imageName = name;
            this.imagePrice = price;
            this.imageStorage = storage;
            this.imageSpecification = specification;
            this.imageURL = url;
        }

        public String getImageName() {
            return imageName;
        }
        public String getImagePrice() {
        return imagePrice;
    }
        public String getImageStorage() {
        return imageStorage;
    }
        public String getImageSpecification() {
        return imageSpecification;
    }
        public String getImageURL() {
            return imageURL;
        }
    }

