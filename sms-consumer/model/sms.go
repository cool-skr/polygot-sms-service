package model

type SmsEvent struct {
	UserID      string `json:"userId" bson:"userId"`
	PhoneNumber string `json:"phoneNumber" bson:"phoneNumber"`
	Message     string `json:"message" bson:"message"`
}