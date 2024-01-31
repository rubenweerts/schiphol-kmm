package org.schiphol.data.remote.fixtures.flight

import org.schiphol.data.remote.fixtures.JsonFixture

object FlightRouteFixture : JsonFixture {
    const val id = "A20230919EJU8674"
    const val number = "EJU8674"

    override fun jsonOk() = """
        {
          "identifier": "$id",
          "fisFlight": {
            "_links": {
              "self": {
                "href": "https://acc.schiphol.dev/api/fis/flight/arrival/20230919/${number}"
              },
              "mainFlight": {
                "href": "https://acc.schiphol.dev/api/fis/flight/arrival/20230919/${number}"
              },
              "contact": {
                "href": "https://acc.schiphol.dev/en/airline/easyjet-europe-eju"
              },
              "website": {
                "href": "https://acc.schiphol.dev/en/arrivals/flight/$id"
              },
              "locations": {
                "href": "https://acc.schiphol.dev/api/maps/itinerary?baggageClaims%5B0%5D=19&terminal=4"
              }
            },
            "identifier": "$id",
            "subscriptionTopicForNotifications": "acc-$id-en",
            "flightNumber": {
              "label": "$number",
              "mainFlight": null,
              "alsoKnownAs": []
            },
            "airline": {
              "iataCode": "EC",
              "icaoCode": "",
              "nvlsCode": "",
              "label": "Unknown"
            },
            "operator": {
              "iataCode": "EC",
              "icaoCode": "",
              "nvlsCode": "",
              "label": "Unknown"
            },
            "aircraft": {
              "registration": null,
              "type": {
                "iataCode": "32S-32N",
                "label": "Airbus A320 Neo",
                "shortLabel": "AIRBUS A320 NEO"
              }
            },
            "locations": {
              "gate": null,
              "pier": null,
              "nearestLounge": null,
              "terminal": {
                "number": "4"
              },
              "baggageClaims": {
                "label": "19",
                "belts": [
                  "19"
                ]
              }
            },
            "route": {
              "direction": {
                "code": "A",
                "label": "Arrival"
              },
              "from": {
                "iataCode": "LGW",
                "label": "London Gatwick"
              },
              "to": {
                "iataCode": "AMS",
                "label": "Amsterdam"
              },
              "stops": [],
              "isASchengenRoute": false
            },
            "scheduled": {
              "on": {
                "dateAndTime": "2023-09-19T10:35:00+02:00",
                "date": "2023-09-19",
                "time": "10:35:00",
                "timeZone": "+02:00",
                "unixTimestamp": 1695112500,
                "isObsolete": false
              }
            },
            "expected": {
              "on": {
                "dateAndTime": "2023-09-19T10:30:00+02:00",
                "date": "2023-09-19",
                "time": "10:30:00",
                "timeZone": "+02:00",
                "unixTimestamp": 1695112200
              },
              "baggageOnBelt": {
                "dateAndTime": "2023-09-19T11:00:05+02:00",
                "date": "2023-09-19",
                "time": "11:00:05",
                "timeZone": "+02:00",
                "unixTimestamp": 1695114005
              },
              "isExpectedOnAnotherDate": false,
              "isEarly": true,
              "isLate": false
            },
            "contactInformation": {
              "name": "easyJet Europe (EJU)",
              "phoneNumber": "+31 20 794 6405",
              "alternatePhoneNumber": "+44 330 551 5151",
              "website": "http://www.easyjet.com/",
              "emailAddress": null,
              "image": null
            },
            "status": {
              "label": "On schedule",
              "name": "on_schedule",
              "isFinal": false,
              "willNeverArrive": false
            }
          },
          "legs": [
            {
              "id": "string",
              "number": "$number",
              "status": {
                "name": "scheduled",
                "label": "On schedule"
              },
              "departure": {
                "location": {
                  "city": "London",
                  "timeZone": "Europe/London",
                  "airport": {
                    "name": "London Gatwick",
                    "iataCode": "LGW"
                  }
                },
                "terminal": 42,
                "checkIn": {
                  "startDateTime": null,
                  "endDateTime": null,
                  "rows": [
                    "10",
                    "11"
                  ]
                },
                "baggageDropOff": [
                  {
                    "startDateTime": null,
                    "endDateTime": null,
                    "rows": [
                      "2",
                      "3"
                    ]
                  }
                ],
                "gate": {
                  "name": "C9",
                  "type": "unknown",
                  "isChanged": false
                },
                "scheduledDateTime": "2023-09-19T09:00:00+02:00",
                "estimatedDateTime": "2023-09-19T09:00:15+02:00",
                "actualDateTime": "2023-09-19T09:00:05+02:00"
              },
              "arrival": {
                "location": {
                  "city": "Amsterdam",
                  "timeZone": "Europe/Amsterdam",
                  "airport": {
                    "name": "Amsterdam Schiphol",
                    "iataCode": "AMS"
                  }
                },
                "terminal": "4",
                "gate": null,
                "baggageClaim": {
                  "belts": [
                    "19"
                  ]
                },
                "scheduledDateTime": "2023-09-19T10:35:00+02:00",
                "estimatedDateTime": "2023-09-19T10:45:00+02:00",
                "actualDateTime": "2023-09-19T10:40:00+02:00"
              }
            }
          ]
        }
    """
}
