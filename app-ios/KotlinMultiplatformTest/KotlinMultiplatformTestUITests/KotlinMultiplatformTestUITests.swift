//
//  KotlinMultiplatformTestUITests.swift
//  KotlinMultiplatformTestUITests
//
//  Created by Evan Tatarka on 6/2/19.
//  Copyright © 2019 EvanTatarka. All rights reserved.
//

import XCTest
import commonTest
import EarlGrey

class KotlinMultiplatformTestUITests: XCTestCase {

    override func setUp() {
        continueAfterFailure = false
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    func testEarlGrey() {
        let app = XCUIApplication()
        app.launch()
        let text = app.staticTexts["text"]
        XCTAssertEqual("Hello World!", text.label)
//        EarlGrey.selectElement(with: grey_accessibilityID("text"))
//            .assert(grey_text("Hello World!"))
    }

    func testEarlGreyRunner() throws {
        try EarlGreyRunner().runKotlinTests()
    }

    func testXCUIRunner() throws {
        try XCUIRunner().runKotlinTests()
    }
}
