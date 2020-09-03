/*
 * Copyright  2020 Vonage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.vonage.quickstart.verify;

import com.nexmo.client.VonageClient;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;
import com.nexmo.client.verify.VerifyRequest;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class StartVerificationWithWorkflow {
    public static void main(String[] args) {
        configureLogging();

        String VONAGE_API_KEY = envVar("VONAGE_API_KEY");
        String VONAGE_API_SECRET = envVar("VONAGE_API_SECRET");

        String RECIPIENT_NUMBER = envVar("RECIPIENT_NUMBER");
        String VONAGE_BRAND = envVar("BRAND_NAME");

        VerifyRequest request = new VerifyRequest(RECIPIENT_NUMBER,VONAGE_BRAND);

        request.setWorkflow(VerifyRequest.Workflow.TTS_TTS);

        VonageClient client = VonageClient.builder().apiKey(VONAGE_API_KEY).apiSecret(VONAGE_API_SECRET).build();
        VerifyResponse response = client.getVerifyClient().verify(request);

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.printf("RequestID: %s", response.getRequestId());
        } else {
            System.out.printf("ERROR! %s: %s", response.getStatus(), response.getErrorText());
        }
    }
}