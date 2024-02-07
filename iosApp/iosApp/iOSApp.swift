import SwiftUI
import shared

@main
struct iOSApp: App {
    init(){
        // Initialize KOIN so we can use DI inside the commonMain/shared module
        KoinHelperKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
