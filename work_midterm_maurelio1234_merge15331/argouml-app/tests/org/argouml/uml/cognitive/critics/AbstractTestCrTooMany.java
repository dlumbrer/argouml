// $Id$
// Copyright (c) 2005-2007 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.uml.cognitive.critics;

import junit.framework.TestCase;
import org.argouml.model.InitializeModel;

import org.argouml.model.Model;

public abstract class AbstractTestCrTooMany extends TestCase {

    protected AbstractCrTooMany cr;

    protected Object model;

    protected Object dm;

    int threshold;

    public AbstractTestCrTooMany(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        InitializeModel.initializeDefault();
        model = Model.getModelManagementFactory().createModel();
    }

    protected abstract void createNewModelElement();

    public void testNoThresholdPredicate2() {
        cr.setThreshold(0);
        // all tests should consider the threshold as inclusive value
        assertFalse(cr.predicate2(dm, null));
    }

    public void testPredicate2() {
        for (int i = 0; i < 5; i++) {
            createNewModelElement();
        }
        cr.setThreshold(10);
        assertFalse(cr.predicate2(dm, null));
        cr.setThreshold(6);
        assertFalse(cr.predicate2(dm, null));
        cr.setThreshold(5);
        assertFalse(cr.predicate2(dm, null));
        cr.setThreshold(4);
        assertTrue(cr.predicate2(dm, null));
        cr.setThreshold(1);
        assertTrue(cr.predicate2(dm, null));
    }

    public void testThreshold() {
        cr.setThreshold(5);
        assertTrue(cr.getThreshold() == 5);
    }

    // this test is a bit stupid, but nevertheless...
    public void testGetWizardClass() {
        assertTrue(cr.getWizardClass(null).equals(WizTooMany.class));
    }

}
