package org.intrade.trading

import org.scalatest.FunSuite

class ResponseTest extends FunSuite {
  test("should process error response") {
    val request =
        <xmlrequest requestOp="someOperation"/>
    val node =
      <tsResponse requestOp="getLogin" resultCode="-1" timestamp="1319901651372" timetaken="387">
        <errorcode>0</errorcode>
        <faildesc>The password you entered does not match your username.</faildesc>
        <sessionData>ANONYMOUS</sessionData>
      </tsResponse>

    val response = Response(request, node, _ => "hello")

    expect(387) {
      response.timetaken
    }
    expect(1319901651372L) {
      response.timestamp
    }
    expect(-1) {
      response.resultCode
    }
    expect("getLogin") {
      response.requestOp
    }
    expect(Option(0)) {
      response.errorcode
    }
    expect("The password you entered does not match your username.") {
      response.faildesc
    }
    expect("ANONYMOUS") {
      response.sessionData
    }
    expect(request) {
      response.request
    }
    expect(node) {
      response.response
    }
    expect(Option.empty) {
      response.payload
    }
  }

  test("should parse response") {
    val request =
        <xmlrequest requestOp="someOperation"/>
    val node =
      <tsResponse timetaken="538" timestamp="1318942052571" resultCode="0"
                  requestOp="getLogin" exchange="intrade">
        <username>baker.alex</username>
        <faildesc>Ok</faildesc>
        <sessionData>603204b866264cdbc78ccff59520f5b5ZACED000574000A62616B65722E616C6578ZBD484FEB7A946E3933B1C850250BE18A977BB705C836806D299B3DC35E553511B421F472B1A608482AB1F6B89FB7F42AA47254A8442B00C142A206558BA611F022AC06B504B8CD067AD90C9F4EAE89EDC20A2CDC2A69680819921F46D0772BBAAF317DB7A104423E8339500597FEE6A9FA229567E935B958693BACEA617BFAB4C3765DE31ACFFB7C0D2112B363593A036DE5F70C05BB14B92C75D72FC432138C2D4DBA1FB827FA1B927B5A4CE4D7294AF734280DFDCCC1E189296AE892E0E1D7FDA85D4E42151F18BB61D3A07C7671853C28116C71A1A759EC3E4733D3BE68437AE5BB8C20313DF35ED98D8C0C40BD20ECD1CE80AD9610903B2DBD61BE93B6DB4B3F49E8F9CCBACFA0DCA2F05D25358022AF0B399CCBB202D7909BE3723F33F6F2E7DCDB1FC389BF</sessionData>
      </tsResponse>

    val response = Response(request, node, _ => "hello")

    expect(538) {
      response.timetaken
    }
    expect(1318942052571L) {
      response.timestamp
    }
    expect(0) {
      response.resultCode
    }
    expect("getLogin") {
      response.requestOp
    }
    expect("Ok") {
      response.faildesc
    }
    expect("603204b866264cdbc78ccff59520f5b5ZACED000574000A62616B65722E616C6578ZBD484FEB7A946E3933B1C850250BE18A977BB705C836806D299B3DC35E553511B421F472B1A608482AB1F6B89FB7F42AA47254A8442B00C142A206558BA611F022AC06B504B8CD067AD90C9F4EAE89EDC20A2CDC2A69680819921F46D0772BBAAF317DB7A104423E8339500597FEE6A9FA229567E935B958693BACEA617BFAB4C3765DE31ACFFB7C0D2112B363593A036DE5F70C05BB14B92C75D72FC432138C2D4DBA1FB827FA1B927B5A4CE4D7294AF734280DFDCCC1E189296AE892E0E1D7FDA85D4E42151F18BB61D3A07C7671853C28116C71A1A759EC3E4733D3BE68437AE5BB8C20313DF35ED98D8C0C40BD20ECD1CE80AD9610903B2DBD61BE93B6DB4B3F49E8F9CCBACFA0DCA2F05D25358022AF0B399CCBB202D7909BE3723F33F6F2E7DCDB1FC389BF") {
      response.sessionData
    }
    expect(request) {
      response.request
    }
    expect(node) {
      response.response
    }
    expect(Option("hello")) {
      response.payload
    }
    expect(Option.empty) {
      response.errorcode
    }
  }
}