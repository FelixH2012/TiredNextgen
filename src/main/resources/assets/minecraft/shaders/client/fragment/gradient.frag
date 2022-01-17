
uniform float time;
uniform vec2 mouse;
uniform vec2 resolution;

void main() {

    // Normalized pixel coordinates (from 0 to 1)
    vec2 uv = gl_FragColor/resolution.xy;

    // Time varying pixel color
    vec3 col = 0.5 + 0.5*cos(uv.xyx+vec3(144,244,244));

    // Output to screen
    gl_FragColor = vec4(col,1.0);
}