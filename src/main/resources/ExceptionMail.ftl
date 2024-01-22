<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Exception Details</title>
    <style>
      body {
        font-family: "Arial", sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f8f9fa;
      }

      .container {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: #ffffff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
      }

      .header {
        text-align: center;
        background-color: #f8f9fa;
        padding: 10px;
      }

      .exception-section {
        margin-top: 20px;
      }

      .exception-section h4 {
        color: #007bff;
        margin-bottom: 10px;
      }

      .stack-trace {
        background-color: #f8f9fa;
        padding: 10px;
        border-radius: 5px;
        overflow-x: auto;
      }
    </style>
  </head>

  <body>
    <div class="container">
      <div class="header">
        <h2>An Exception Occurred</h2>
      </div>

      <!-- Service Details Details -->
      <div class="exception-section">
        <h4>Service Details</h4>
        <p><b>Service Name:</b> ${serviceName}</p>
      </div>

      <!-- Server Details Details -->
      <div class="exception-section">
        <h4>Deployed Server Details</h4>
        <p><b>Server:</b> ${deployedServerName} Server</p>
      </div>

      <!-- Application Status Details -->
      <div class="exception-section">
        <h4>Application Status</h4>
        <p><b>Status:</b> ${applicationStatus}</p>
      </div>

      <!-- Method Details -->
      <div class="exception-section">
        <h4>Method Details</h4>
        <p><b>Method:</b> ${methodName}</p>
      </div>

      <!-- Input Parameters -->
      <div class="exception-section">
        <h4>Input Parameters</h4>
        <p><b>Parameters:</b> ${methodParams}</p>
      </div>

      <!-- Exception Message -->
      <div class="exception-section">
        <h4>Exception Message</h4>
        <p><b>Exception:</b> ${exceptionMsg}</p>
      </div>

      <!-- Stack Trace -->
      <div class="exception-section">
        <h4>Stack Trace</h4>
        <div class="stack-trace">
          <pre>
            ${exceptionStackTrace}
          </pre>
        </div>
      </div>

      <!-- Additional Content -->
      <div class="exception-section">
        <h4>Additional Information</h4>
        <p>
          This is additional content that provides more context about the
          exception.
        </p>
      </div>
    </div>
  </body>
</html>
