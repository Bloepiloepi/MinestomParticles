package io.github.bloepiloepi.particles.shapes;

import io.github.bloepiloepi.particles.shapes.builder.BezierBuilder;
import io.github.bloepiloepi.particles.shapes.builder.CircleBuilder;
import io.github.bloepiloepi.particles.shapes.builder.MultiPolygonBuilder;
import io.github.bloepiloepi.particles.shapes.builder.PolygonBuilder;
import net.minestom.server.utils.Position;
import org.jetbrains.annotations.NotNull;

public abstract class ParticleShape {
    protected static final double EPSILON = 0.00001;

    public abstract @NotNull ParticleIterator<?> iterator(ShapeOptions options);

    public static @NotNull PolygonBuilder polygon() {
        return new PolygonBuilder();
    }

    public static @NotNull MultiPolygonBuilder multiPolygon() {
        return new MultiPolygonBuilder();
    }

    public static @NotNull ParticleSingle single(@NotNull Position position) {
        return new ParticleSingle(position);
    }

    public static @NotNull ParticleLine line(@NotNull Position pos1, @NotNull Position pos2) {
        return new ParticleLine(pos1, pos2);
    }

    public static @NotNull BezierBuilder bezier(@NotNull Position start, @NotNull Position end) {
        return new BezierBuilder().start(start).end(end);
    }

    public static @NotNull CircleBuilder circle(@NotNull Position position) {
        return new CircleBuilder().position(position);
    }

    public static @NotNull MultiPolygon cube(@NotNull Position position, double width, double height, double depth) {
        return multiPolygon()
                .lineStart(position)
                .lineTo(position.clone().add(width, 0, 0))
                .lineTo(position.clone().add(width, 0, depth))
                .lineTo(position.clone().add(0, 0, depth))

                .jumpTo(position.clone().add(0, height, 0))
                .lineTo(position.clone().add(width, height, 0))
                .lineTo(position.clone().add(width, height, depth))
                .lineTo(position.clone().add(0, height, depth))

                .jumpTo(position)
                .lineTo(position.clone().add(0, height, 0))
                .jumpTo(position.clone().add(width, 0, 0))
                .lineTo(position.clone().add(width, height, 0))
                .jumpTo(position.clone().add(width, 0, depth))
                .lineTo(position.clone().add(width, height, depth))
                .jumpTo(position.clone().add(0, 0, depth))
                .lineTo(position.clone().add(0, height, depth))

                .build();
    }
}
