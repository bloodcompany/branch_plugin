#import "BranchPlugin.h"
#if __has_include(<branch_plugin/branch_plugin-Swift.h>)
#import <branch_plugin/branch_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "branch_plugin-Swift.h"
#endif

@implementation BranchPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftBranchPlugin registerWithRegistrar:registrar];
}
@end
