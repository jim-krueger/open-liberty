/*******************************************************************************
 * Copyright (c) 2013, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.security.openidconnect.token;

import com.ibm.websphere.ras.annotation.Trivial;

@Trivial
public enum PayloadParameter {
    EXP, NBF, IAT, ISS, AUD, JTI, TYP, SUB, AUTH_TIME, AZP, NONCE, AT_HASH, ACR, AMR;
}
