precision highp float;

uniform vec2 resolution;
uniform float time;

// Based on Morgan McGuire @morgan3d
// https://www.shadertoy.com/view/4dS3Wd
float hash(vec2 p) {
    vec3 p3 = fract(vec3(p.xyx) * 0.13); p3 += dot(p3, p3.yzx + 3.333);
    return fract((p3.x + p3.y) * p3.z);
}

float noise(vec2 x) {
    vec2 i = floor(x);
    vec2 f = fract(x);

    // Four corners in 2D of a tile
    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));

    // Same code, with the clamps in smoothstep and common subexpressions
    // optimized away.
    vec2 u = f * f * (3.0 - 2.0 * f);
    return mix(a, b, u.x) + (c - a) * u.y * (1.0 - u.x) + (d - b) * u.x * u.y;
}

void main() {
    vec2 uv = (gl_FragCoord.xy - resolution * .5) / resolution.yy + 0.5;

    vec3 c1 = vec3(1., 1., 0.); // yellow
    vec3 c2 = vec3(.5, 0., .5); // purple

    float n = 15.0;

    float noise = noise(uv*1.35);
    float gridX = mod(noise * n + sin(time), 1.0);
    float gridY = mod(noise * n * 0.5 + cos(time), 1.0);

    float outlineGridX = smoothstep(1.3, .7, gridX);
    float outlineGridY = smoothstep(1.5, .6, gridY);

    vec3 color = vec3(  vec2( outlineGridX+gridX*0.5-0.2, outlineGridY ), 4. * sin(uv.x + uv.y) );

    color *= mix(c2, c1, 1. - uv.y);

    gl_FragColor = vec4(color, 1.0);
}