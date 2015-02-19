package com.nwalsh.library;

import com.xmlcalabash.library.DefaultStep;
import com.xmlcalabash.core.XProcConstants;
import com.xmlcalabash.core.XMLCalabash;
import com.xmlcalabash.io.WritablePipe;
import com.xmlcalabash.core.XProcRuntime;
import com.xmlcalabash.util.TreeWriter;

import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmNode;
import com.xmlcalabash.runtime.XAtomicStep;

@XMLCalabash(
        name = "nw:hello-world",
        type = "{http://nwalsh.com/xmlcalabash/steps}hello-world")

public class HelloWorld extends DefaultStep {
    private WritablePipe result = null;

    public HelloWorld(XProcRuntime runtime, XAtomicStep step) {
        super(runtime,step);
    }

    public void setOutput(String port, WritablePipe pipe) {
        result = pipe;
    }

    public void reset() {
        result.resetWriter();
    }

    public void run() throws SaxonApiException {
        super.run();

        TreeWriter tree = new TreeWriter(runtime);
        tree.startDocument(step.getNode().getBaseURI());
        tree.addStartElement(XProcConstants.c_result);
        tree.startContent();
        tree.addText("Hello World");
        tree.addEndElement();
        tree.endDocument();
        result.write(tree.getResult());
    }
}
