import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var viewModel: ViewModel = ViewModel()
    
    @State private var flightNumber :String = CommonDefaults().defaultFlightId()
    
    var body: some View {
        VStack(
            spacing: 3
        ){
            HStack(){
                TextField(
                    "Flight Number",
                    text: $flightNumber
                )
                Button("Add Flight"){
                    viewModel.addFlight(flightId: flightNumber)
                }
            }
        }
    }
}

extension ContentView{
    class ViewModel: ObservableObject{
        let flightRepository: FlightRepository
        
        init() {
            let dependencyProvider = KoinDependencyProvider.shared
            flightRepository = dependencyProvider.flightRepository
        }
        
        func addFlight(flightId: String){
            
        }
        
        func observeFlights() async {
            for await flightResult in flightRepository.observeFlights(){
                
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
