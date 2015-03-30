/**
 * Copyright (c) 2009-2015, rultor.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the rultor.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.rultor.web;

import com.rultor.spi.Pulse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import javax.imageio.ImageIO;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.takes.Take;
import org.takes.rs.RsPrint;

/**
 * Test case for {@link TkTicks}.
 * @author Yegor Bugayenko (yegor@tpc2.com)
 * @version $Id$
 * @since 1.50
 */
public final class TkTicksTest {

    /**
     * TkSVG can render SVG.
     * @throws Exception If some problem inside
     */
    @Test
    public void rendersSvg() throws Exception {
        final Take home = new TkTicks(
            Arrays.asList(
                new Pulse.Tick(1L, 1L, 1),
                new Pulse.Tick(2L, 1L, 1)
            )
        );
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new RsPrint(home.act()).printBody(baos);
        final BufferedImage image = ImageIO.read(
            new ByteArrayInputStream(baos.toByteArray())
        );
        MatcherAssert.assertThat(
            image.getHeight(),
            // @checkstyle MagicNumber (1 line)
            Matchers.equalTo(400)
        );
    }

    /**
     * TkSVG can render SVG without ticks.
     * @throws Exception If some problem inside
     */
    @Test
    public void rendersSvgWithoutTicks() throws Exception {
        final Take home = new TkTicks(
            Collections.<Pulse.Tick>emptyList()
        );
        MatcherAssert.assertThat(
            new RsPrint(home.act()).printBody(),
            Matchers.notNullValue()
        );
    }

}