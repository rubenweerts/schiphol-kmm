import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var viewModel: ViewModel = ViewModel()
    
    var body: some View {
        VStack(
            spacing: 3
        ){
            Button("Fetch flight"){
                self.viewModel.fetchFlight()
                self.viewModel.testRepositoryImplExample()
            }
            
            switch(viewModel.flightViewState){
                case .loading:
                    Text("Loading...")
                case .empty:
                    Text("Click the button")
                case .error(let error):
                    Text(error)
                case .content(let content):
                    Text("\(content.flightRoute.debugDescription)")
            }
        }
    }
}

extension ContentView{
    class ViewModel: ObservableObject{
        @Published private(set) var flightViewState = FlightViewState.empty
        
        let flightRepository: FlightRepository
        let testRepository: TestRepository
        
        init() {
            let dependencyProvider = KoinDependencyProvider.shared
            flightRepository = dependencyProvider.flightRepository
            testRepository = dependencyProvider.testRepository
        }
        
        func testRepositoryImplExample(){
            let normalResult = testRepository.normalFun()
            print("normalResult = \(normalResult)")
            
            // coroutine suspend fun
            Task{
                let suspendResult = try await   testRepository.suspendFun()
                print("suspendResult = \(suspendResult)")
            }
            
            // flow
            Task{
                let asyncIterator = testRepository.flowFun()
                
                for await value in asyncIterator{
                    await MainActor.run{
                        print("flowFun next = \(value)")
                    }
                }
            }
        }
        
        func fetchFlight(){
            flightViewState = FlightViewState.loading
            
            
            Task{
                do{
                    let result = try await flightRepository.fetchFlight()
                    print(result.debugDescription)
                    
                    await MainActor.run{
                        flightViewState = .content(FlightViewState.Content(flightRoute: result))
                    }
                }catch let error{
                    await MainActor.run{
                        flightViewState = .error(error.localizedDescription)
                    }
                }
            }
        }
    }
}

enum FlightViewState{
    struct Content{
        let flightRoute: FlightRoute?
    }
    
    case empty
    case loading
    case content(Content)
    case error(String)
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
