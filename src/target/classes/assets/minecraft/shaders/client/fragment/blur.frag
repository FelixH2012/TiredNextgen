#version 120

uniform sampler2D currentTexture;
uniform vec2 texelSize;
uniform vec2 coords;
uniform float blurRadius;
uniform float blursigma;

    float CalcGauss(float x, float sigma)
    {

        float sigmaMultiplication =  ((blursigma * blursigma));
        if (blursigma <= 0.0)
        return 0.0;
        return (exp(-.5 * x * x / (sigmaMultiplication))/ blursigma) * .4;
    }


void main() {
    vec3 color = vec3(0.0);
    for (float radiusF = -blurRadius; radiusF <= blurRadius; radiusF++) {
        color += texture2D(currentTexture, gl_TexCoord[0].st + radiusF * texelSize * coords).rgb * CalcGauss(radiusF, blurRadius / 2);
    }
    gl_FragColor = vec4(color, 1.0);


}